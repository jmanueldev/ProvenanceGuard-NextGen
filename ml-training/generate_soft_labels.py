# ml-training/generate_soft_labels.py
model.eval()

soft_labels = []
features_list = []

for graph in dataset:
    x, edge_index, batch = graph

    with torch.no_grad():
        prob = model(x, edge_index, batch).item()

    # Extract simple features (same as mobile)
    features = extract_features(graph)

    features_list.append(features)
    soft_labels.append(prob)