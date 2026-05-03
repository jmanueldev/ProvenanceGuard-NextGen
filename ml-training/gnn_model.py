# ml-training/gnn_model.py
import torch
import torch.nn as nn
from torch_geometric.nn import GCNConv, global_mean_pool

class MalwareGNN(nn.Module):

    def __init__(self):
        super().__init__()
        self.conv1 = GCNConv(100, 64)
        self.conv2 = GCNConv(64, 32)
        self.fc = nn.Linear(32, 1)

    def forward(self, x, edge_index, batch):

        x = self.conv1(x, edge_index).relu()
        x = self.conv2(x, edge_index).relu()

        x = global_mean_pool(x, batch)
        return torch.sigmoid(self.fc(x))