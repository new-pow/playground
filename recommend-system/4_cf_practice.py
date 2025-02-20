from reader import MovieLens
from sklearn.model_selection import train_test_split
import numpy as np
import pandas as pd
from util import RMSE

data = MovieLens()

users = data.users
users.set_index('user_id')
movies = data.movies
movies.set_index('movie_id')

# 모델별 RMSE 계산                                 
def score(model):
    id_pairs = zip(x_test['user_id'], x_test['movie_id'])
    y_pred = np.array([model(user, movie) for (user, movie) in id_pairs]) # model() 을 이용하여 예측값을 구함

    y_true = np.array(x_test['rating']) # 실제값을 가져옴
    return RMSE(y_true, y_pred) # RMSE 함수로 정확도를 구함

### 데이터 셋
x = data.ratings.copy() # ratings 를 복사
y = x['user_id'] # user_id 만 가져옴
x_train, x_test, y_train, y_test = train_test_split(x, y, 
                                                    test_size=0.25, # test_size=0.25 는 전체의 25% 를 test 로 나눔
                                                    stratify=y) # stratify=y 는 y 의 비율을 유지하면서 나눔
ratings_matrix = x_train.pivot(index='user_id', columns='movie_id', values='rating')

### 코사인 유사도 계산
from sklearn.metrics.pairwise import cosine_similarity

matrix_dummy = ratings_matrix.copy().fillna(0) # NaN 을 0으로 채움
user_similarity = cosine_similarity(matrix_dummy, matrix_dummy) # 코사인 유사도 계산
user_similarity = pd.DataFrame(user_similarity, index = ratings_matrix.index, columns = ratings_matrix.index) # 데이터 프레임으로 변환

### 주어진 영화의 가중평균값을 계산하는 함수
def CF_simple(user_id, movie_id):
    if movie_id in ratings_matrix.columns:
        sim_scores = user_similarity[user_id].copy() # 현재 사용자와 다른 사용자 간의 유사도
        movie_ratings = ratings_matrix[movie_id].copy() # 현재 영화에 대한 모든 사용자의 평점
        none_rating_idx = movie_ratings[movie_ratings.isnull()].index # 현재 영화를 평가하지 않은 사용자의 인덱스
        movie_ratings = movie_ratings.dropna() # 현재 영화를 평가한 사용자만을 이용
        sim_scores = sim_scores.drop(none_rating_idx)
        mean_rating = np.dot(sim_scores, movie_ratings) / sim_scores.sum() # 가중평균. 유사도와 평점을 곱한 값의 합을 유사도의 합으로 나눔
    else:
        mean_rating = 3.0 # 평균 3.0 설정함
    return mean_rating

### 정확도 계싼
score = score(CF_simple)
print(score) # 1.0158524731888603
