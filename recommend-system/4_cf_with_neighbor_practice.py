import os
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from reader import MovieLens
from util import RMSE

### 데이터 로그
data = MovieLens()

### 유사 집단의 크기를 미리 정하기 위해 기존 score 함수에 neighbor_size 추가
def score(model, neighbor_size=0):
    # 테스트 데이터의 user_id와 movie_id 간 pair 를 맞춰 튜플형원소 리스트 데이터를 만듬
    id_pairs = zip(x_test['user_id'], x_test['movie_id'])
    # 모든 사용자-영화 짝에 대해 주어진 예측 모델에 의해 예측된 평점을 계산
    y_pred = np.array([model(user, movie, neighbor_size) for (user, movie) in id_pairs])
    # 실제 평점을 가져옴
    y_true = np.array(x_test['rating'])
    return RMSE(y_true, y_pred)

### 테스트 데이터 셋
x = data.ratings.copy()
y = x['user_id']

x_train, x_test, y_train, y_test = train_test_split(x,y,test_size=0.25,stratify=y) # stratify=y 는 y 의 비율을 유지하면서 나눔
ratings_matrix = x_train.pivot(index='user_id', columns='movie_id', values='rating')

### train set의 모든 가능한 사용자 pair의 코사인 유사도 계산
from sklearn.metrics.pairwise import cosine_similarity
# 코사인 유사도를 구하기 위해 rating 값 복사, 계산 시 NaN 값이 있으면 에러가 발생하므로 0으로 채움
matrix_dummy = ratings_matrix.copy().fillna(0)
# 모든 사용자간 코사인 유사도 계산
user_similarity = cosine_similarity(matrix_dummy, matrix_dummy)
# 데이터 프레임으로 변환
user_similarity = pd.DataFrame(user_similarity,
                               index=ratings_matrix.index,
                               columns=ratings_matrix.index)

### neighbor size 를 정해서 예측치 계산
def CF_knn(user_id, movie_id, neighbor_size=0):
    if movie_id in ratings_matrix.columns:
        sim_scores = user_similarity[user_id].copy()
        movie_ratings = ratings_matrix[movie_id].copy()
        none_rating_idx = movie_ratings[movie_ratings.isnull()].index
        movie_ratings = movie_ratings.dropna()
        sim_scores = sim_scores.drop(none_rating_idx)

        if neighbor_size == 0:
            movie_ratings = np.dot(sim_scores, movie_ratings) / sim_scores.sum() # simple CF
        else:
            if len(sim_scores) > 1:
                neighbor_size = min(neighbor_size, len(sim_scores)) # neighbor_size 가 전체보다 크면 전체로 설정
                sim_scores = np.array(sim_scores)
                movie_ratings = np.array(movie_ratings)
                user_idx = np.argsort(sim_scores)
                sim_scores = sim_scores[user_idx][-neighbor_size:] # 유사도가 큰 neighbor_size 만큼 가져옴
                movie_ratings = movie_ratings[user_idx][-neighbor_size:]
                mean_rating = np.dot(sim_scores, movie_ratings) / sim_scores.sum() # weighted CF
            else:
                mean_rating = 3.0
    else:
        mean_rating = 3.0
    return mean_rating

# 정확도 계산
# 1.0031626861707965 -> 약간 개선됨
print(score(CF_knn, neighbor_size=30))

### 실제 주어진 사용자에 대해 추천을 받는 기능 구현
ratings_matrix = data.ratings.pivot_table(values='rating', index='user_id', columns='movie_id')
matrix_dummy = ratings_matrix.copy().fillna(0)
user_similarity = cosine_similarity(matrix_dummy, matrix_dummy)
user_similarity = pd.DataFrame(user_similarity,
                               index=ratings_matrix.index,
                               columns=ratings_matrix.index)

def recom_movie(user_id, n_items, neighbor__size = 30):
    user_movie = ratings_matrix.loc[user_id].copy()
    for movie in ratings_matrix.columns: # 모든 영화에 대해
        if pd.notnull(user_movie.loc[movie]): # 사용자가 이미 평가한 영화는 제외
            user_movie = user_movie.drop(movie) # 평가한 영화를 제거
        else:
            user_movie.loc[movie] = CF_knn(user_id, movie, neighbor__size)

    movie_sort = user_movie.sort_values(ascending=False)[:n_items]
    recom_movies = data.movies.loc[movie_sort.index]
    recommendations = recom_movies['title']
    return recommendations

# 5개의 영화를 추천
print(recom_movie(user_id=729, n_items=5, neighbor__size=30))

### 최적의 이웃 크기 찾기
for neighbor_size in [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]:
    print("Neighbor size: %d : RMSE = %.4f" % (neighbor_size, score(CF_knn, neighbor_size=neighbor_size)))

# Neighbor size: 10 : RMSE = 0.8130
# Neighbor size: 20 : RMSE = 0.8817
# Neighbor size: 30 : RMSE = 0.9088
# Neighbor size: 40 : RMSE = 0.9238
# Neighbor size: 50 : RMSE = 0.9330
# Neighbor size: 60 : RMSE = 0.9395
    
