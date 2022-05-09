package cz.muni.log4ts.exceptions

class UserAlreadyPresent(message: String) : Exception(message)

class UserNotPresent(message: String) : Exception(message)

class UserNotFound(message: String) : Exception(message)