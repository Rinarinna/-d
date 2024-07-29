package com.example.filestorage

import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository : JpaRepository<FileEntity?, Long?>