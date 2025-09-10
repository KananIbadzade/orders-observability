# orders-observability

A tiny Spring Boot **orders API** wired with Micrometer → **Datadog (US3)**.  
It shows HTTP throughput + latency (p95/p99) and basic JVM metrics. A `/seed` endpoint generates demo traffic so you can see charts quickly.

---

## What’s inside
- Spring Boot 3.3, Java 21
- Micrometer + Datadog registry (pushes directly to Datadog API)
- Endpoints: `POST /orders`, `GET /orders`, `GET /orders/seed?n=150`
- Global metric tag: `application:orders-api`

---

## Quick start

### 0) Prereqs
- **Java 21**
- **Maven** (wrapper included: `./mvnw`)
- A Datadog **API key** (site: **US3**)

### 1) Clone & env
```bash
git clone <your-repo-url>
cd orders-observability

# create your env file (do NOT commit your real key)
cp .env.example .env
# then edit .env and set DD_API_KEY=your_real_key
