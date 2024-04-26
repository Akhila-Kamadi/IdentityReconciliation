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
- **Response:** Returns the created/updated contact.
```json
{
    "id": 1,
    "createdAt": "2024-04-26T10:09:25.422726",
    "updatedAt": "2024-04-26T10:09:25.422762",
    "deletedAt": null,
    "phoneNumber": "123456",
    "email": "lorraine@hillvalley.edu",
    "linkedId": null,
    "linkPrecedence": "PRIMARY"
}
