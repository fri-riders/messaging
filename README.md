# Messaging
`Last version: 0.2`
## Docker compose
- Build app: mvn clean package
- Run: docker-compose up --build

## Endpoints
App is accessible on port 8086.
Example call: `{IP}:8086/v1/messages`

### Messages
* `GET: /v1/messages` Returns list of all messages
* `GET: /v1/messages/{messageId}` Returns message with appropriate id
* `GET: /v1/messages/{messageId}/sender` Returns sender of message with appropriate id
* `GET: /v1/messages/{userId}/sent` Returns users sent messages
* `GET: /v1/messages/{userId}/received` Returns users sent messages
* `POST: /v1/messages` Inserts new message to the database

### Config
* `GET: /v1/config` Returns all configurable values

### Health
* `GET: /health` Returns info about service health
* `POST: /v1/health-demo/healthy` Changes config value `healthy` (parameter is boolean) 

### Metrics
* `GET: /metrics` Returns metrics

