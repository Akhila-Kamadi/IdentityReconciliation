# Identity Reconciliation API

This repository contains the source code for the Identity Reconciliation API, which allows users to manage contacts and reconcile identities based on email addresses and phone numbers.

## Endpoints

### Create Contact
- **URL:** `identityreconciliation.akidev.me/contacts`
- **Method:** POST
- **Description:** Create a new contact.
- **Request Body:**
```json
{
    "phoneNumber": "123456",
    "email": "lorraine@hillvalley.edu"
}
