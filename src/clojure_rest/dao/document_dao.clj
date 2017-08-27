(ns clojure-rest.dao.document-dao
  (:use [korma.core :only [select insert values delete where]])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.tools.logging :as log]
            [clojure-rest.model.document :as document-model]
            [clojure-rest.config.database :as database]))

; -----------------
; -- Korma versions
; -----------------

(defn get-all-documents
  "Gets all documents from the database"
  []
  (select document-model/document))

(defn get-document-by-id
  "Gets only one document using its id"
  [id-document]
  (select document-model/document (where {:id_document id-document})))

(defn create-new-document
  "Adds a new document to the database"
  [document-to-create]
  (log/info (str "create-new-document dao : " document-to-create))
  (insert document-model/document (values document-to-create)))

(defn update-document
  "Updates an existing document on the database"
  [id-document-to-update document-to-update]
  ;(update document-model/document (set-fields ))
  (jdbc/update! database/db-h2-connection :documents document-to-update ["id_document=?" id-document-to-update]))

(defn delete-document
  "Deletes an existing document on the database, returns id of deleted document"
  [id-document-to-delete]
  (delete document-model/document (where {:id_document id-document-to-delete})))
