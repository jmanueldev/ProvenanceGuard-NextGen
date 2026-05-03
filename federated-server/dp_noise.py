import numpy as np

def add_noise(update):
    return update + np.random.normal(0, 0.01, len(update))