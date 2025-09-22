#!/bin/bash

# Hospital Management System - Docker Setup Script
# This script provides an easy way to build and run the application

set -e

echo "🏥 Hospital Management System - Docker Setup"
echo "=============================================="

# Function to check if Docker is running
check_docker() {
    if ! docker info >/dev/null 2>&1; then
        echo "❌ Docker is not running. Please start Docker and try again."
        exit 1
    fi
    echo "✅ Docker is running"
}

# Function to build the application
build_app() {
    echo "🔨 Building Hospital Management application..."
    docker-compose build --no-cache
    echo "✅ Build completed successfully"
}

# Function to start the application
start_app() {
    echo "🚀 Starting Hospital Management application..."
    docker-compose up -d
    echo "✅ Application started successfully"
    echo ""
    echo "🌐 Access points:"
    echo "   - Main API: http://localhost:8085/patients"
    echo "   - H2 Console: http://localhost:8085/h2-console"
    echo "   - Health Check: http://localhost:8085/actuator/health"
    echo ""
    echo "📊 To view logs: docker-compose logs -f"
    echo "🛑 To stop: docker-compose down"
}

# Function to stop the application
stop_app() {
    echo "🛑 Stopping Hospital Management application..."
    docker-compose down
    echo "✅ Application stopped successfully"
}

# Function to show logs
show_logs() {
    echo "📊 Showing application logs..."
    docker-compose logs -f
}

# Function to restart the application
restart_app() {
    echo "🔄 Restarting Hospital Management application..."
    docker-compose restart
    echo "✅ Application restarted successfully"
}

# Function to clean up
cleanup() {
    echo "🧹 Cleaning up Docker resources..."
    docker-compose down -v --remove-orphans
    docker system prune -f
    echo "✅ Cleanup completed"
}

# Main menu
case "$1" in
    "build")
        check_docker
        build_app
        ;;
    "start")
        check_docker
        start_app
        ;;
    "stop")
        check_docker
        stop_app
        ;;
    "restart")
        check_docker
        restart_app
        ;;
    "logs")
        check_docker
        show_logs
        ;;
    "cleanup")
        check_docker
        cleanup
        ;;
    "run")
        check_docker
        build_app
        start_app
        ;;
    *)
        echo "Usage: $0 {build|start|stop|restart|logs|cleanup|run}"
        echo ""
        echo "Commands:"
        echo "  build    - Build the Docker image"
        echo "  start    - Start the application"
        echo "  stop     - Stop the application"
        echo "  restart  - Restart the application"
        echo "  logs     - Show application logs"
        echo "  cleanup  - Clean up Docker resources"
        echo "  run      - Build and start (one command setup)"
        echo ""
        echo "Quick start: $0 run"
        exit 1
        ;;
esac