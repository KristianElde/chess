#!/bin/bash

# Script to add, commit and push to origin/dev

# Exit script if any command fails
set -e

# Define branch variable
branch="dev"

# Add changes to git
echo "Adding changes to git..."
git add .

# Commit changes - prompts user for commit message
echo "Enter commit message: "
read commitMessage
git commit -m "$commitMessage"

# Push changes to GitLab
echo "Pushing changes to GitLab (origin/dev)..."
git push origin $branch

echo "Successfully pushed to GitLab(origin/dev)."