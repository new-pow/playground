import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.metrics.pairwise import cosine_similarity
from reader import MovieLens
from util import RMSE

data = MovieLens()
users = data.users
users.set_index('user_id')
movies = data.movies
movies.set_index('movie_id')
ratings = data.ratings

def score(model, neighbor_size=0):
    id_pairs = zip(x_test['user_id'], x_test['movie_id'])
    y_pred = np.array([model(user,movie,neighbor_size) for (user,movie) in id_pairs])
    y_true = np.array(x_test['rating'])
    return RMSE(y_true, y_pred)

x = ratings.copy()
y = x['user_id']

x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.25, stratify=y)
ratings_matrix = x_train.pivot(index='user_id', columns='movie_id', values='rating')

matrix_dummy = ratings_matrix.copy().fillna(0)
user_similarity = cosine_similarity(matrix_dummy, matrix_dummy)
user_similarity = pd.DataFrame(user_similarity, index=ratings_matrix.index, columns=ratings_matrix.index)

rating_mean = ratings_matrix.mean(axis=1)
rating_bias = (ratings_matrix.T - rating_mean).T

### 신뢰도
rating_binary_1 = np.array(ratings_matrix>0).astype(float) # 평가한 영화에 대해 1, 아니면 0 으로 바뀜
rating_binary_2 = rating_binary_1.T

counts = np.dot(rating_binary_1, rating_binary_2) # 영화별로 공통으로 평가한 사용자의 수를 계산
counts = pd.DataFrame(counts,
                      index = ratings_matrix.index,
                      columns = ratings_matrix.index).fillna(0) # 유저마다 공통으로 평가한 영화의 수를 데이터 프레임으로 만듬

def CF_knn_bias(user_id, movie_id, neighbor_size=0):
    if movie_id in rating_bias:
        sim_scores = user_similarity[user_id].copy()
        movie_ratings = rating_bias[movie_id].copy()

        no_rating = movie_ratings.isnull() # 평점이 없는 영화
        common_counts = counts[user_id] # 현재 사용자와 다른 사용자 간의 공통 평가 영화 수
        low_significance = common_counts < SIG_LEVEL # 신뢰도가 낮은 사용자
        none_rating_idx = movie_ratings[no_rating | low_significance].index # 평점이 없거나 신뢰도가 낮은 사용자의 인덱스

        movie_ratings = movie_ratings.drop(none_rating_idx) # 현재 영화를 평가하지 않은 사용자의 인덱스를 제거
        sim_scores = sim_scores.drop(none_rating_idx) # 평점이 없는 사용자는 제외

        if neighbor_size == 0:
            # simple
            prediction = np.dot(sim_scores, movie_ratings) / sim_scores.sum()
            prediction = prediction + rating_mean[user_id]

        else:
            if len(sim_scores) > MIN_RATINGS: # 최소 평가 횟수 이상인 경우만 계산
                neighbor_size = min(neighbor_size, len(sim_scores))
                sim_scores = np.array(sim_scores)
                movie_ratings = np.array(movie_ratings)
                user_idx = np.argsort(sim_scores) # 유사도를 기준으로 인덱스를 정렬
                sim_scores = sim_scores[user_idx][-neighbor_size:] # 유사도 상위 neighbor_size 만큼 가져옴
                movie_ratings = movie_ratings[user_idx][-neighbor_size:]
                prediction = np.dot(sim_scores, movie_ratings) / sim_scores.sum()
                prediction = prediction + rating_mean[user_id]
            else:
                prediction = rating_mean[user_id]
    else:
        prediction = rating_mean[user_id]

    if prediction < 1:
        prediction = 1
    elif prediction > 5:
        prediction = 5

    return prediction

SIG_LEVEL = 3
MIN_RATINGS = 3

print(score(CF_knn_bias, neighbor_size=30)) 
# sig_level = 3, min_ratings=5, 0.9447745687974097
# sig_level = 3, min_ratings=3, 0.9464544815816943

# 조금씩 수정해나가면서 정확도를 높여나갈 수 있음