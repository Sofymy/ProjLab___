package com.bme.projlab.di

import com.bme.projlab.data.datasource.*
import com.bme.projlab.data.network.ApiInterface
import com.bme.projlab.data.repository.*
import com.bme.projlab.domain.loginoptions.*
import com.bme.projlab.domain.modificationoptions.ModificationOptions
import com.bme.projlab.domain.modificationoptions.ModifyPassword
import com.bme.projlab.domain.modificationoptions.ModifyUsername
import com.bme.projlab.domain.repository.*
import com.bme.projlab.domain.signupoptions.SignupOptions
import com.bme.projlab.domain.signupoptions.SignupWithFacebook
import com.bme.projlab.domain.signupoptions.SignupWithGoogle
import com.bme.projlab.domain.signupoptions.SignupWithPassword
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    @Provides
    fun provideLogin(
        repository: LoginRepository
    ) = LoginOptions(
        loginWithPassword = LoginWithPassword(repository),
        loginWithFacebook = LoginWithFacebook(repository),
        loginWithGoogle = LoginWithGoogle(repository),
        loggedInUser = LoginImmediately(repository)
    )

    @Provides
    fun provideSignup(
        repository: SignupRepository
    ) = SignupOptions(
        signupWithPassword = SignupWithPassword(repository),
        signupWithFacebook = SignupWithFacebook(repository),
        signupWithGoogle = SignupWithGoogle(repository)
    )

    @Provides
    fun provideModification(
        repository: ModificationRepository
    ) = ModificationOptions(
        modifyUsername = ModifyUsername(repository),
        modifyPassword = ModifyPassword(repository)
    )

    @Provides
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideLoginRepository(firebaseAuth: FirebaseAuth):LoginRepository = LoginRepositoryImpl(firebaseAuth)

    @Provides
    fun provideSignupRepository(firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore): SignupRepository = SignupRepositoryImpl(firebaseAuth)

    @Provides
    fun provideModificationRepository(firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore): ModificationRepository = ModificationRepositoryImpl(firebaseAuth, firebaseFirestore)

    @Provides
    fun provideUserRepository(firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore): UserRepository = UserRepositoryImpl(firebaseAuth, firebaseFirestore)

    @Provides
    fun provideDestinationRepository(firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore): DestinationRepository = DestinationRepositoryImpl(firebaseAuth, firebaseFirestore)

    @Provides
    fun provideSearchDestinationRepository(firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore): SearchDestinationRepository = SearchDestinationRepositoryImpl(firebaseAuth, firebaseFirestore)

    @Provides
    fun provideReceivedTripsRepository(firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore): ReceivedTripsRepository = ReceivedTripsRepositoryImpl(firebaseAuth, firebaseFirestore)

    @Provides
    fun provideSearchResultsRepository(flightDataSource: FlightDataSource): SearchResultsRepository = SearchResultsRepositoryImpl(flightDataSource)

    @Provides
    fun provideTopRepository(firebaseAuth: FirebaseAuth): TopRepository = TopRepositoryImpl(firebaseAuth)

    @Provides
    fun provideAirportRepository(airportDataSource: AirportDataSource): AirportRepository = AirportRepositoryImpl(airportDataSource)

    @Provides
    fun provideDataSourceRepositoryImpl(
        airportDataSource: AirportDataSource,
        accommodationDataSource: AccommodationDataSource,
        destinationDataSource: DestinationDataSource,
        flightDataSource: FlightDataSource,
        transferInfoDataSource: TransferInfoDataSource,
        transferOptionsDataSource: TransferOptionsDataSource,
        tripDataSource: TripDataSource,
        userDataSource: UserDataSource
    ): DataSourceRepositoryImpl = DataSourceRepositoryImpl(
        airportDataSource,
        accommodationDataSource,
        destinationDataSource,
        flightDataSource,
        transferInfoDataSource,
        transferOptionsDataSource,
        tripDataSource,
        userDataSource
    )

    @Provides
    fun provideDataSourceAccommodation(firebaseFirestore: FirebaseFirestore): AccommodationDataSource = AccommodationDataSource(firebaseFirestore)

    @Provides
    fun provideDataSourceDestination(firebaseFirestore: FirebaseFirestore): DestinationDataSource = DestinationDataSource(firebaseFirestore)

    @Provides
    fun provideDataSourceTransferInfo(firebaseFirestore: FirebaseFirestore): TransferInfoDataSource = TransferInfoDataSource(firebaseFirestore)

    @Provides
    fun provideDataSourceTransferOptions(firebaseFirestore: FirebaseFirestore): TransferOptionsDataSource = TransferOptionsDataSource(firebaseFirestore)

    @Provides
    fun provideDataSourceTrip(firebaseFirestore: FirebaseFirestore): TripDataSource = TripDataSource(firebaseFirestore)

    @Provides
    fun provideDataSourceUser(firebaseFirestore: FirebaseFirestore): UserDataSource = UserDataSource(firebaseFirestore)

}