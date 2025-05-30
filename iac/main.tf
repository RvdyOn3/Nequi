terraform {
  required_providers {
    mongodbatlas = {
      source  = "mongodb/mongodbatlas"
      version = "~> 1.13.0"
    }
  }
}

provider "mongodbatlas" {
  public_key  = var.mongo_public_key
  private_key = var.mongo_private_key
}

resource "mongodbatlas_project" "project" {
  name   = "nequi"
  org_id = var.mongo_org_id
}

resource "mongodbatlas_cluster" "cluster" {
  project_id             = mongodbatlas_project.project.id
  name                   = "nequi"
  cluster_type           = "REPLICASET"
  provider_name          = "AWS"
  provider_region_name   = "US_EAST_1"
  provider_instance_size_name = "M0"
  mongo_db_major_version = "6.0"
  disk_size_gb           = 10
  auto_scaling_disk_gb_enabled = true
}

resource "mongodbatlas_database_user" "dbuser" {
  username           = "developer"
  password           = "Nequi2025."
  project_id         = mongodbatlas_project.project.id
  auth_database_name = "admin"
  roles {
    role_name     = "readWriteAnyDatabase"
    database_name = "admin"
  }
}

resource "mongodbatlas_project_ip_access_list" "access" {
  project_id = mongodbatlas_project.project.id
  cidr_block = "0.0.0.0/0"
  comment    = "Allow all IPs for testing"
}
