# ml-training/gnn_dataset.py
import torch

def build_graph(edges):

    node_map = {}
    edge_index = []
    edge_attr = []

    def get_id(x):
        if x not in node_map:
            node_map[x] = len(node_map)
        return node_map[x]

    for e in edges:
        src = get_id(e["from"])
        dst = get_id(e["to"])

        edge_index.append([src, dst])
        edge_attr.append([hash(e["action"]) % 10])

    edge_index = torch.tensor(edge_index).t().contiguous()
    edge_attr = torch.tensor(edge_attr, dtype=torch.float)

    x = torch.eye(len(node_map))  # simple node features

    return x, edge_index, edge_attr