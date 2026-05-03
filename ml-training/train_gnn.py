# ml-training/train_gnn.py
from gnn_model import MalwareGNN
import torch

model = MalwareGNN()
optimizer = torch.optim.Adam(model.parameters(), lr=1e-3)

for epoch in range(30):
    pred = model(x, edge_index, batch)
    loss = ((pred - y) ** 2).mean()

    loss.backward()
    optimizer.step()
    optimizer.zero_grad()