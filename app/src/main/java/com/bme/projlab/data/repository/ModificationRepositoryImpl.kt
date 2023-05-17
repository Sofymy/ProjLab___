package com.bme.projlab.data.repository

import android.content.ContentValues
import android.util.Log
import com.bme.projlab.domain.model.response.UsernameResponse
import com.bme.projlab.domain.repository.ModificationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class ModificationRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : ModificationRepository{

    override suspend fun modifyUsername(username: String): UsernameResponse {
        return try {
            val user = firebaseAuth.currentUser
            if (user != null) {
                modifyUsernameAuth(user, username)
                modifyUsernameFirestore(user, username)
            }
            UsernameResponse.Success
        }catch (e: Exception){
            UsernameResponse.Error
        }
    }

    override suspend fun modifyPassword(password: String) {
        firebaseAuth.currentUser?.updatePassword(password)
    }

    private fun modifyUsernameAuth(user: FirebaseUser, username: String){
        val profileUpdates = userProfileChangeRequest {
            displayName = username
        }
        user.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "User profile updated.")
                }
            }
    }

    private fun modifyUsernameFirestore(user: FirebaseUser, username: String){
        firebaseFirestore.collection("users")
            .document(user.uid).update("username", username).addOnSuccessListener {
                Log.d(ContentValues.TAG, "User profile in Firestore updated.")
            }
        val usernameUid = hashMapOf(
            "uid" to user.uid
        )
        firebaseFirestore.collection("usernames").document(username).set(usernameUid)
    }
}