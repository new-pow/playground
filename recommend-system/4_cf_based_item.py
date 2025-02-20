import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.metrics.pairwise import cosine_similarity
from reader import MovieLens
from util import RMSE

data = MovieLens()
users = data.users.set_index('user_id')
movies = data.movies
movies = movies.set_index('movie_id')
ratings = data.ratings

def score(model):
    id_pairs = zip(x_test['user_id'], x_test['movie_id'])
    y_pred = np.array([model(user, movie) for (user, movie) in id_pairs])
    y_true = np.array(x_test['rating'])
    return RMSE(y_true, y_pred)

x = ratings.copy()
y = x['user_id']

x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.25, stratify=y)
rating_matrix = x_train.pivot(index='user_id',
                              columns='movie_id',
                              values='rating')

###
rating_matrix_t = np.transpose(rating_matrix) # 전치행렬 -> 아이템 기반 협업 필터링을 위해 전치행렬로 변경
matrix_dummy = rating_matrix_t.copy().fillna(0) # NaN 을 0으로 채움
item_similarity = cosine_similarity(matrix_dummy, matrix_dummy) # 코사인 유사도 계산
item_similarity = pd.DataFrame(item_similarity,
                               index=rating_matrix_t.index,
                               columns=rating_matrix_t.index) # 데이터 프레임으로 변환

def CF_IBCF(user_id, movie_id):
    if movie_id in item_similarity.columns:
        sim_scores = item_similarity[movie_id]
        user_rating = rating_matrix_t[user_id] # 현재 사용자의 모든 영화에 대한 평점

        non_rating_idx = user_rating[user_rating.isnull()].index # 현재 사용자가 평가하지 않은 영화
        user_rating = user_rating.dropna() # 현재 사용자가 평가한 영화만을 이용
        sim_scores = sim_scores.drop(non_rating_idx) # 현재 사용자가 평가하지 않은 영화의 유사도 제거
        mean_rating = np.dot(sim_scores, user_rating) / sim_scores.sum()
    else:
        mean_rating = 3.0
    return mean_rating

score = score(CF_IBCF)
print(score) # 1.018
# 1.0188009804644809