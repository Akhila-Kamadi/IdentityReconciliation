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
    "email": "lorraine@hillvalley.edu",
    "phoneNumber": "123456"
}
```
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
```

### Get All Contacts
- **URL:** `identityreconciliation.akidev.me/contacts`
- **Method:** GET
- **Description:** Retrieve a list of all contacts.
- **Response:** Returns a list of contacts.

### Identify Contact
- **URL:** `identityreconciliation.akidev.me/contacts/identify`
- **Method:** POST
- **Description:** Reconcile the identity of a customer based on provided email address and/or phone number.
- **Request Body:**
```json
{
    "email": "mcfly@hillvalley.edu",
    "phoneNumber": "123456"
}
```
- **Response:** Returns the created/updated contact.
```json
{
    "contact": {
        "primaryContactId": 1,
        "emails": ["lorraine@hillvalley.edu","mcfly@hillvalley.edu"],
        "phoneNumbers": ["123456"],
        "secondaryContactIds": [2]
    }
}
```

## Getting Started

To run the Identity Reconciliation API locally, follow these steps:

1. Clone this repository: `git clone https://github.com/Akhila-Kamadi/identity-reconciliation.git`
2. Navigate to the project directory: `cd identity-reconciliation`
3. Build the project using Maven: `mvn clean install`
4. Start the server: `mvn spring-boot:run`

Once the server is running, you can access the API endpoints as described in the README.
