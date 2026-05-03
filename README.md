```markdown
# 🛡️ ProvenanceGuard-NextGen

**Real-time, on-device malware detection for Android using network provenance graphs, lightweight ML, and optional on-device LLM reasoning.**

---

## 🚀 Overview

ProvenanceGuard-NextGen is a **privacy-preserving mobile security system** that detects malicious behavior by analyzing **runtime data flows**, not just static signatures.

Unlike traditional antivirus solutions, this system:

- Captures **live network traffic (TCP/UDP)** using Android VPN APIs
- Builds a **provenance graph of app behavior**
- Extracts behavioral features in real time
- Runs **on-device ML inference (TensorFlow Lite)**
- Optionally uses **on-device LLM (Gemma via MLC)** for reasoning
- Supports **federated learning** for continuous improvement

---

## 🧠 Core Idea

Instead of asking:

> “Is this app known malware?”

We ask:

> “Is this behavior suspicious right now?”

---

## 🏗️ Architecture

```

VPN Traffic
↓
Packet Parser (IP/TCP/UDP)
↓
Provenance Graph (time-windowed)
↓
Feature Extraction
↓
ML Classifier (TFLite)
↓
(Optional) LLM Reasoning (Gemma via MLC)
↓
Decision Engine

```

---

## 📦 Monorepo Structure

```

ProvenanceGuard-NextGen/

├── android-app/        # Android client (VPN + UI + ML inference)
│   ├── app/
│   │   ├── vpn/
│   │   ├── graph/
│   │   ├── ml/
│   │   ├── slm/
│   │   ├── ui/
│   │   └── service/
│   ├── assets/
│   │   ├── malware_model.tflite
│   │   └── gemma_config.json
│   └── build.gradle
│
├── core-engine/        # Core detection engine
│   ├── packet/
│   ├── tcp_reassembly/
│   ├── udp_parser/
│   ├── provenance_graph/
│   ├── feature_extractor/
│   └── risk_engine/
│
├── federated-server/   # Federated learning backend
│   ├── main.py
│   ├── aggregator.py
│   ├── dp_noise.py
│   └── model_store/
│
├── ml-training/        # Model training pipeline
│   ├── dataset_loader.py
│   ├── train.py
│   ├── export_tflite.py
│   └── evaluation.py
│
├── slm-runtime/        # On-device LLM runtime (MLC)
│   ├── mlc_config.json
│   ├── prompt_engine.kt
│   └── streaming_adapter.kt
│
├── benchmarks/         # Evaluation scripts
│   ├── drebin_eval.py
│   ├── cicmal_eval.py
│   └── metrics.py
│
└── README.md

```

---

## 📡 Key Components

### 📶 Network Capture (VPN-Based)

- Captures real device traffic without root
- Parses IP packets and extracts TCP/UDP payloads
- Supports TCP stream reassembly

---

### 🔗 Provenance Graph Engine

Maintains a **sliding time-window graph** of system behavior:

- **Nodes:** apps, domains, system resources
- **Edges:** actions (network, exec, SMS, contacts)

Example pattern:

```

Contacts → App → Network → SMS

````

Used to detect:
- Data exfiltration
- Command & control (C2)
- Privilege abuse chains

---

### 🔍 Feature Extraction

Graph → numerical features:

- Contacts → network transitions
- Execution → network activity
- SMS after network usage
- Unique domains contacted
- Graph density

---

### ⚡ On-Device ML (TFLite)

- Lightweight binary classifier
- Runs in **<200ms**
- Fully offline
- Trained on:
  - Drebin dataset
  - CICMalDroid dataset

---

### 🤖 Optional LLM Reasoning

Uses **Google Gemma (via MLC runtime)**:

- Provides human-readable explanations
- Handles edge-case reasoning
- Streams tokens in real time

Only triggered when:
- ML confidence is low
- Behavior is ambiguous
- User requests explanation

---

### 🌍 Federated Learning

- Devices send **model updates only**
- No raw data leaves device
- Server aggregates updates
- Differential privacy noise applied

---

### 📊 Evaluation

Benchmarks supported:

- Drebin
- CICMalDroid

Target performance:

| Metric           | Target |
|----------------|--------|
| Precision       | > 0.92 |
| Recall          | > 0.88 |
| False Positives | < 3%   |
| ML Latency      | <200ms |
| LLM Latency     | 1–3s   |

---

## 🛠️ Setup

### 🔧 Requirements

- Android Studio (latest)
- Python 3.9+
- TensorFlow / TFLite
- FastAPI
- MLC LLM runtime (optional)

---

### 📱 Build Android App

```bash
cd android-app
./gradlew assembleDebug
````

Install:

```bash
adb install app-debug.apk
```

---

### 🧠 Train Model

```bash
cd ml-training
python train.py
python export_tflite.py
```

---

### 🌍 Run Federated Server

```bash
cd federated-server
uvicorn main:app --reload
```

---

### 📊 Run Benchmarks

```bash
cd benchmarks
python drebin_eval.py
```

---

## ⚙️ Device Optimization

| Device Tier | Strategy                            |
| ----------- | ----------------------------------- |
| High-end    | Full graph + LLM enabled            |
| Mid-range   | Reduced graph window, selective LLM |
| Low-end     | ML + rules only                     |

---

## 🔐 Privacy

* No raw user data leaves device
* Federated learning only
* Differential privacy enforced
* Offline detection supported

---

## 🧪 Example Detection Flow

1. App reads contacts
2. Opens network connection
3. Sends data to unknown domain
4. Triggers SMS

→ Graph captures sequence
→ ML flags anomaly
→ LLM explains behavior

---

## 🧠 Key Insight

* **Graph + ML = ~95% detection capability**
* LLM is optional and used for:

  * Explainability
  * Edge-case reasoning

---

## 📈 Future Work

* Graph Neural Networks (GNNs)
* On-device continual learning
* Zero-day malware simulation
* iOS support
* Threat intelligence feeds

---

## 🤝 Contributing

Areas of contribution:

* Android performance optimization
* ML model improvements
* Graph algorithms
* Privacy enhancements

---

## ⚠️ Disclaimer

This project is for **research and educational purposes only**.
Not intended as a replacement for commercial security products.

---

## 📜 License

MIT License

```
```
