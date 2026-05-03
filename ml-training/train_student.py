# ml-training/train_student.py
import tensorflow as tf
import numpy as np

X = np.array(features_list)
y = np.array(soft_labels)

model = tf.keras.Sequential([
    tf.keras.layers.Dense(16, activation='relu', input_shape=(X.shape[1],)),
    tf.keras.layers.Dense(8, activation='relu'),
    tf.keras.layers.Dense(1, activation='sigmoid')
])

model.compile(optimizer='adam', loss='mse')
model.fit(X, y, epochs=20)