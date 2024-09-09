## Subscription and User Roles Explained

In our project, we handle how users can subscribe to our platform and how different user roles (like normal users and administrators) are managed. Below is an explanation of how these features work, presented in a simple way for everyone to understand.

---

### 1. Subscription Service

**What It Does**:  
The subscription service manages all the actions related to users signing up for paid or free services. This part of the system ensures that users can subscribe, check their subscription status, and cancel if needed.

**Main Features**:
- **Create a Subscription**: When a user signs up, the system creates a new subscription based on the dates they choose.
- **View Subscriptions**: Users can see all their current and past subscriptions.
- **Cancel a Subscription**: Users can cancel their active subscription if they no longer want it.
- **Check if a User is Subscribed**: The system can check whether a user is currently a subscriber.

---

### 2. Subscription Data Storage

**What It Does**:  
This part of the system is responsible for storing and retrieving information about user subscriptions in the database.

**Main Features**:
- **Find Subscriptions by User**: The system can look up all the subscriptions connected to a specific user.

---

### 3. Subscription Controller

**What It Does**:  
The controller acts as the "middleman" between the user and the subscription service. It handles requests like creating or canceling subscriptions and shows the user the right information.

**Main Features**:
- **Show Subscription Form**: Displays the form where users can sign up for a subscription.
- **Create a Subscription**: After filling out the form, this feature processes the user’s subscription and saves it.
- **View Subscriptions**: Displays all the subscriptions that a user has signed up for.
- **Cancel Subscription**: Allows a user to cancel their active subscription.
- **Check Subscription Status**: Lets users know if they are currently subscribed.

---

### 4. User Roles

**What It Does**:  
In our system, users can have different "roles," which decide what they can and cannot do on the platform. For example, regular users can access basic features, while administrators (admins) have more control and can manage other users.

**Main Features**:
- **Role Management**: Each role (like "User" or "Admin") can have multiple users assigned to it. We can add users to roles or remove them.

---

### 5. Role Management Service

**What It Does**:  
This service helps manage who gets assigned to which role (User, Admin, etc.) and allows us to add, update, or delete roles in the system.

**Main Features**:
- **Create a New Role**: Allows us to create new roles for the system.
- **Find a Role**: Helps us find an existing role by name (e.g., "Admin").
- **Update a Role**: Allows us to update details about existing roles.
- **Delete a Role**: Enables us to delete roles that are no longer needed.

