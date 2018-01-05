# Messaging

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

