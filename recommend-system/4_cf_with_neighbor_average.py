import os
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.metrics.pairwise import cosine_similarity
from reader import MovieLens
from util import RMSE

### 데이터 셋 준비
data = MovieLens()
users = data.users
users.set_index('user_id')

movies = data.movies
movies.set_index('movie_id')

ratings = data.ratings

### score
def score(model, neighbor_size=0):
    # 테스트 데이터의 user_id와 movie_id 간 pair 를 맞춰 튜플형원소 리스트 데이터를 만듬
    id_pairs = zip(x_test['user_id'], x_test['movie_id'])
    # 모든 사용자-영화 짝에 대해 주어진 예측 모델에 의해 예측된 평점을 계산 및 리스트형 데이터 생성
    y_pred = np.array([model(user, movie, neighbor_size) for (user, movie) in id_pairs]) # model() 을 이용하여 예측값을 구함
    # 실제 평점을 가져옴
    y_true = np.array(x_test['rating'])
    return RMSE(y_true, y_pred) # RMSE 함수로 정확도를 구함

x = ratings.copy()
y = x['user_id'] # user_id 만 가져옴

x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.25, stratify=y) # train_test_split() 는 실제 데이터인가? test_size=0.25 는 전체의 25% 를 test 로 나눔
ratings_matrix = x_train.pivot(index='user_id', columns='movie_id', values='rating')

matrix_dummy = ratings_matrix.copy().fillna(0)
user_similarity = cosine_similarity(matrix_dummy, matrix_dummy)
user_similarity = pd.DataFrame(user_similarity,
                               index=ratings_matrix.index,
                               columns=ratings_matrix.index) # 데이터 프레임으로 변환 'user_id' 에 대한 'movie_id' 의 코사인 유사도를 계산한 데이터 프레임

### 사용자 평가 성향을 고려한 함수
rating_mean = ratings_matrix.mean(axis = 1) # axis=1 은 행을 기준으로 계산 (평균)
rating_bias = (ratings_matrix.T - rating_mean).T # 평점의 편차를 계산 # T 는 전치행렬을 의미 전치행렬은 행과 열을 바꾸는 것을 의미. 유저 기준으로 계산해야해서 전치행렬을 2번 적용

def CF_knn_bias(user_id, movie_id, neighbor_size=0):
    if movie_id in rating_bias.columns:
        sim_scores = user_similarity[user_id].copy()
        movie_ratings = rating_bias[movie_id].copy()
        none_rating_idx = movie_ratings[movie_ratings.isnull()].index
        movie_ratings = movie_ratings.drop(none_rating_idx) # 현재 영화를 평가하지 않은 사용자의 인덱스를 제거
        sim_scores = sim_scores.drop(none_rating_idx) # 평점이 없는 사용자는 제외

        if neighbor_size == 0:
            prediction = np.dot(sim_scores, movie_ratings) / sim_scores.sum()
            prediction = prediction + rating_mean[user_id] # 사용자 평균을 더함. 예측치를 편차로 계산했기 때문.

        else:
            if len(sim_scores) >1 :
                neighbor_size = min(neighbor_size, len(sim_scores)) 
                sim_scores = np.array(sim_scores)
                movie_ratings = np.array(movie_ratings)
                user_idx = np.argsort(sim_scores) # 유사도를 기준으로 인덱스를 정렬       
                sim_scores = sim_scores[user_idx][-neighbor_size:]
                movie_ratings = movie_ratings[user_idx][-neighbor_size:] # 실제 평점을 가져옴
                prediction = np.dot(sim_scores, movie_ratings) / sim_scores.sum()
                prediction = prediction + rating_mean[user_id]
            else:
                prediction = rating_mean[user_id] # 사용자의 평균 평점을 사용
    else:
        prediction = rating_mean[user_id]
    return prediction

print(score(CF_knn_bias, neighbor_size=30)) # 정확도 계산 #0.9458426728517532
# 지난 시간보다 정확도가 개선됨
