import os
import pandas as pd

class MovieLens:
    def __init__(self):
        self.users = None # None 으로 초기화 이렇게 하면 객체 생성시 users 를 넣어주지 않아도 됨
        self.movies = None
        self.ratings = None

        base_src = './data'
        u_user_src = os.path.join(base_src, 'u.user')
        u_cols = ['user_id', 'age','sex','occupation', 'zip_code']
        self.users = pd.read_csv(u_user_src, sep='|', names= u_cols, encoding='latin-1')
        # print(self.users.head())

        u_item_src = os.path.join(base_src, 'u.item')
        i_cols = ['movie_id', 'title','release date','video release date', 'IMDb URL', 'unknown', 'Action', 'Adventure',
        'Animation', 'Children\'s', 'Comedy', 'Crime', 'Documentary', 'Drama', 'Fantasy',
        'Film-Noir', 'Horror', 'Musical', 'Mystery', 'Romance', 'Sci-Fi', 'Thriller', 'War', 'Western']

        self.movies = pd.read_csv(u_item_src, sep='|', names= i_cols, encoding='latin-1')
        # print(self.movies.head())

        u_data_src = os.path.join(base_src, 'u.data')
        r_cols = ['user_id', 'movie_id', 'rating', 'timestamp']
        self.ratings = pd.read_csv(u_data_src, sep='\t', names=r_cols, encoding='latin-1')
        self.ratings.head() # head() 는 처음 5개만 보여줌
        # print(self.ratings.head())

    def ready_index(self):
        # 인덱스를 설정
        self.users = self.users.set_index('user_id')
        self.movies = self.movies.set_index('movie_id')
        self.ratings = self.ratings.set_index('user_id')

    def ready_data(self):
        # 전처리
        self.ratings = self.ratings.drop('timestamp', axis=1) # drop() 은 행이나 열을 삭제하는 함수. axis=1 은 열을 삭제
        self.movies = self.movies[['movie_id','title']]
