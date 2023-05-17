const functions = require("firebase-functions");
const admin = require("firebase-admin");
admin.initializeApp();

exports.createUser = functions.auth.user().onCreate((user) => {
  return admin.firestore().collection("users").doc(user.uid).set({
    email: user.email,
    uid: user.uid,
    username: user.displayName,
    heartedTrips: [],
    receivedTrips: [],
    visitedLocations: [],
    badges: [],
  });
});
