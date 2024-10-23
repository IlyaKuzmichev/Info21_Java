db = db.getSiblingDB("authdb");

db.createUser({
    user: "admin",
    pwd: "admin",
    roles: [
        {
            role: "readWrite",
            db: "authdb"
        }
    ]
});
