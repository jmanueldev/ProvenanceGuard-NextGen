from fastapi import FastAPI
import numpy as np

app = FastAPI()
global_model = np.zeros(3)

@app.post("/update")
def update(update: list[float]):
    global global_model
    global_model += np.array(update) * 0.1
    return {"ok": True}

@app.get("/model")
def get_model():
    return global_model.tolist()

@app.post("/domain_update")
def update_domain(data: dict):
    domain = data["domain"]
    score = data["score"]

    store[domain] = (store.get(domain, 0.5) + score) / 2
    return {"ok": True}