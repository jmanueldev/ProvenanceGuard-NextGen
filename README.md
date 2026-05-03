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
