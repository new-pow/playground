from reader import MovieLens
import numpy as np
from rmse import RMSE 

data = MovieLens()
data.ready_index()

# 인기 제품 방식 추천 function
def recom_movie(n_items):
    movie_mean = data.ratings.groupby(['movie_id'])['rating'].mean() # mean() 은 평균을 구하는 함수. movie_id 별로 묶어서 rating 의 평균을 구함
    movie_sort = movie_mean.sort_values(ascending=False)[:n_items] # sort_values() 는 값을 정렬하는 함수. ascending=False 는 내림차순으로 정렬
    recom_movies = data.movies.loc[movie_sort.index] # loc[] 는 행을 가져오는 함수. movie_sort 의 index 값으로 movie 를 가져옴
    recommendations = recom_movies['title'] # title 만 가져옴
    return recommendations



# 정확도 측정
rmse = []
movie_mean = data.ratings.groupby(['movie_id'])['rating'].mean() # movie_id 별로 묶어서 rating 의 평균을 구함
for user in set(data.ratings.index): # user_id 를 set() 으로 중복을 제거하여 가져옴
    y_true = data.ratings.loc[user]['rating'] # user_id 별 rating 값을 가져옴
    # best-seller 방식
    y_pred = movie_mean[data.ratings.loc[user]['movie_id']] # movie_mean 에 user_id 별 movie_id 값을 넣어서 예측값을 구함
    accuracy = RMSE(y_true, y_pred) # RMSE 함수로 정확도
    rmse.append(accuracy) # RMSE 함수로 정확도 측정

print(recom_movie(5)) # 5개의 인기 제품을 추천
print("--------------------")
print(np.mean(rmse)) # 정확도의 평균을 구함