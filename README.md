# Identity Reconciliation API

This repository contains the source code for the Identity Reconciliation API, which allows users to manage contacts and reconcile identities based on email addresses and phone numbers.

## Endpoints

### Create Contact
- **URL:** `identityreconciliation.akidev.me/contacts`
- **Method:** POST
- **Description:** Create a new contact if it doesn't exist, update an existing contact if the provided email or phone number matches an existing contact, or create a secondary contact if the provided email or phone number is associated with a different contact.
- **Request Body:**
```json
{
    "phoneNumber": "123456",
    "email": "lorraine@hillvalley.edu"
}
