from reader import MovieLens
from sklearn.model_selection import train_test_split
from rmse import RMSE
import numpy as np
import pandas as pd

data = MovieLens()
data.ready_data() # ready_data() 함수로 전처리
x = data.ratings.copy() # ratings 를 복사
y = x['user_id'] # user_id 만 가져옴

x_train, x_test, y_train, y_test = train_test_split(x, y, 
                                                    test_size=0.25, # test_size=0.25 는 전체의 25% 를 test 로 나눔
                                                    stratify=y) # stratify=y 는 y 의 비율을 유지하면서 나눔

# 모델별 RMSE 계산                                 
def score(model):
    id_pairs = zip(x_test['user_id'], x_test['movie_id'])
    y_pred = np.array([model(user, movie) for (user, movie) in id_pairs]) # model() 을 이용하여 예측값을 구함

    y_true = np.array(x_test['rating']) # 실제값을 가져옴
    return RMSE(y_true, y_pred) # RMSE 함수로 정확도를 구함

# best-seller 함수로 정확도 계산
train_mean = x_train.groupby(['movie_id'])['rating'].mean() # train 데이터의 movie_id 별 rating 의 평균을 구함

def best_seller(user_id, movie_id):
    try: # index 가 없을 때 예외처리
        rating = train_mean[movie_id]
    except:
        rating = 3.0 # 없을 때는 3.0 으로 처리
        pass
    return rating

print(score(best_seller)) # best-seller 방식으로 정확도를 구함
# 1.0292855531371259

# 성별에 따른 예측값 계산
merged_ratings = pd.merge(x_train, data.users) # x_train 과 users 를 user_id 로 묶어서 병합
users = data.users.set_index('user_id') # user_id 를 인덱스로 설정

g_mean = merged_ratings[['movie_id', 'sex', 'rating']].groupby(['movie_id', 'sex'])['rating'].mean() # 성별에 따른 영화의 평균

rating_matrix = x_train.pivot(index='user_id', 
                              columns='movie_id', 
                              values='rating') # pivot() 은 행과 열을 바꾸는 함수
# full matrix 로 만들어졌습니다

# 성별 기준 추천
def cf_gender(user_id, movie_id):
    if movie_id in rating_matrix.columns:
        gender = users.loc[user_id]['sex']
        if gender in g_mean[movie_id].index:
            gender_rating = g_mean[movie_id][gender]
        else:
            gender_rating = 3.0
    else: 
        gender_rating = 3.0
    return gender_rating

print(score(cf_gender)) # 성별에 따른 정확도를 구함
# 1.0305407327602687
# 생각보다 성능이 좋지 않음
