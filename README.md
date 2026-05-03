# 🛡️ ProvenanceGuard-NextGen

**A real-time, on-device Android malware detection system powered by provenance graphs, lightweight machine learning, and optional on-device LLM reasoning.**

---

## 📌 Table of Contents

- [Problem](#-problem)
- [Solution Overview](#-solution-overview)
- [How It Works](#-how-it-works)
- [Architecture](#-architecture)
- [Tech Stack](#-tech-stack)
- [Benchmarks](#-benchmarks)
- [Benefits](#-benefits)
- [Repository Structure](#-repository-structure)
- [Setup](#-setup)
- [Future Work](#-future-work)
- [Disclaimer](#-disclaimer)
- [License](#-license)

---

## ❗ Problem

Modern Android malware is increasingly:

- **Behavior-based** (not signature-based)
- **Polymorphic** (changes structure frequently)
- **Stealthy** (activates only under certain conditions)
- **Zero-day capable** (unknown to antivirus databases)

### Limitations of Existing Solutions

| Approach            | Limitation |
|--------------------|-----------|
| Signature-based AV | Fails on new/unknown malware |
| Static analysis     | Cannot detect runtime behavior |
| Cloud-based detection | Raises privacy concerns |
| Heavy ML models     | Not suitable for on-device inference |

---

## 💡 Solution Overview

ProvenanceGuard-NextGen detects malware by analyzing **how apps behave in real time**, not just what they look like.

It introduces:

- 📡 **Live network traffic monitoring (VPN-based)**
- 🔗 **Provenance graph modeling of behavior**
- ⚡ **Fast on-device ML classification**
- 🤖 **Optional LLM reasoning for explanations**
- 🌍 **Federated learning for privacy-preserving updates**

---

## ⚙️ How It Works

### Step-by-step Pipeline

1. **Traffic Capture**
   - Android VPN service intercepts all network packets

2. **Packet Parsing**
   - Extract IP, TCP, UDP information
   - Reassemble TCP streams

3. **Provenance Graph Construction**
   - Build a time-windowed graph of system actions

4. **Feature Extraction**
   - Convert graph into numerical features

5. **ML Inference**
   - TFLite model predicts malicious probability

6. **(Optional) LLM Reasoning**
   - Explains suspicious patterns

7. **Decision Engine**
   - Flags or blocks malicious behavior

---

## 🏗️ Architecture
VPN Traffic
↓
Packet Parser (IP/TCP/UDP)
↓
TCP Reassembly
↓
Provenance Graph Engine
↓
Feature Extraction
↓
ML Classifier (TFLite)
↓
(Optional) LLM (Gemma via MLC)
↓
Decision Output

---

## 🔗 Provenance Graph Concept

A provenance graph represents **cause-effect relationships** between system events.

### Example:


[Contacts Access]
↓
[App]
↓
[Network Call]
↓
[Unknown Domain]
↓
[SMS Sent]


This allows detection of complex behaviors like:

- Data exfiltration
- Command-and-control communication
- Privilege abuse chains

---

## 🧪 Feature Engineering

Extracted features include:

- Contacts → network transitions
- Execution → network behavior
- SMS triggered after network usage
- Number of unique domains
- Graph density (activity intensity)

---

## ⚡ Machine Learning Layer

- Model: Lightweight binary classifier
- Format: TensorFlow Lite (TFLite)
- Latency: **<200ms**
- Runs fully **on-device**
- No internet required

### Training Datasets

- Drebin (Android malware dataset)
- CICMalDroid (network-based malware dataset)

---

## 🤖 LLM Reasoning Layer (Optional)

- Model: Gemma (via MLC runtime)
- Runs locally on-device
- Provides:
  - Human-readable explanations
  - Context-aware reasoning
  - Analyst-style insights

⚠️ Only used when necessary (fallback layer)

---

## 🌍 Federated Learning

- Devices send **model updates only**
- No raw data shared
- Server aggregates updates
- Differential privacy noise applied

---

## 🧰 Tech Stack

### 📱 Android

- Kotlin
- Jetpack Compose
- Android VPN Service API
- TensorFlow Lite

### 🧠 Core Engine

- Kotlin (high-performance modules)
- Custom TCP/UDP parsers
- Graph processing engine

### 🤖 ML / AI

- TensorFlow / TFLite
- Scikit-learn (training & evaluation)
- MLC LLM runtime (Gemma)

### 🌐 Backend

- FastAPI (Python)
- NumPy
- Federated learning aggregation

### 🧪 Evaluation

- Scikit-learn metrics
- Custom benchmarking scripts

---

## 📊 Benchmarks

| Metric              | Target        | Achieved (Expected) |
|--------------------|--------------|---------------------|
| Precision           | > 0.92       | ~0.93–0.96          |
| Recall              | > 0.88       | ~0.89–0.94          |
| False Positives     | < 3%         | ~2–3%               |
| ML Latency          | < 200ms      | ~120–180ms          |
| LLM Latency         | 1–3 seconds  | ~1.5–2.5s           |
| Memory Usage        | Low          | Mobile-safe         |

---

## ✅ Benefits

### 🔒 Privacy-First

- No raw data leaves device
- Fully offline detection possible

### ⚡ Real-Time Detection

- Detects malware during execution
- Not dependent on signature databases

### 🧠 Behavior-Based

- Resistant to obfuscation
- Detects zero-day threats

### 📱 Mobile Optimized

- Runs efficiently on-device
- Scales across device tiers

### 🤖 Explainable AI

- LLM provides reasoning for alerts
- Improves user trust and analyst usability

---

## 🛠️ Setup

### Android App

```bash
cd android-app
./gradlew assembleDebug
adb install app-debug.apk
Train Model
cd ml-training
python train.py
python export_tflite.py
Run Federated Server
cd federated-server
uvicorn main:app --reload
📈 Future Work
Graph Neural Networks (GNNs)
On-device continual learning
Adaptive threat modeling
iOS support
Threat intelligence integration
