#!/bin/bash

# Script to push code to both GitHub and GitLab

# Exit script if any command fails
set -e

# Define branch variable
branch="master" # Change to your default branch if different, e.g., "master"

# Pull latest changes from GitLab
echo "Pulling latest changes from GitLab (origin)..."
git pull origin $branch

# Optionally, pull latest changes from GitLab to ensure sync
echo "Pulling latest changes from GitHub..."
git pull github $branch

# Add changes to git
echo "Adding changes to git..."
git add .

# Commit changes - prompts user for commit message
echo "Enter commit message: "
read commitMessage
git commit -m "$commitMessage"

# Push changes to GitLab
echo "Pushing changes to GitLab (origin)..."
git push origin $branch

# Push changes to GitHub
echo "Pushing changes to GitHub..."
git push github $branch

echo "Successfully pushed to both GitHub and GitLab."