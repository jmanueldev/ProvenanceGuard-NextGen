from sklearn.metrics import precision_score, recall_score

def run(y_true, y_pred):
    print(precision_score(y_true, y_pred))
    print(recall_score(y_true, y_pred))