import pandas as pd

def load_drebin(path):
    df = pd.read_csv(path)
    X = df.drop("label", axis=1).values
    y = df["label"].values
    return X, y

def load_cic(path):
    df = pd.read_csv(path)
    X = df.iloc[:, :-1].values
    y = df.iloc[:, -1].values
    return X, y