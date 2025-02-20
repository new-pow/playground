import numpy as np

    
    # RMSE (Root Mean Squared Error) : 실제 값과 예측 값의 차이를 제곱하여 평균을 낸 값의 제곱근 계산
def RMSE(y_true, y_pred): # y_true : 실제 값, y_pred : 예측 값
    return np.sqrt(
        np.mean(
            (np.array(y_true) - np.array(y_pred))**2
            )
        )
