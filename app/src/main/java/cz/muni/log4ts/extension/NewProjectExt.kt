package cz.muni.log4ts.extension

import cz.muni.log4ts.data.entities.NewProject
import cz.muni.log4ts.data.entities.Project

fun NewProject.toProject(id: String): Project =
    Project(
        id = id,
        namespaceId = this.namespaceId,
        name = this.name,
        users = mutableListOf()
    )