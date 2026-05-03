from sklearn.metrics import precision_score, recall_score

def evaluate(y_true, y_pred):
    print("Precision:", precision_score(y_true, y_pred))
    print("Recall:", recall_score(y_true, y_pred))