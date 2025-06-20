#!/bin/sh

# -----------------------------------------------------------------------------
# Gradle start up script for UN*X
# -----------------------------------------------------------------------------

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS=""

APP_NAME="Gradle"
APP_BASE_NAME=$(basename "$0")

# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD="maximum"

warn() {
  echo "$APP_NAME: $*" >&2
}

die() {
  echo
  echo "$APP_NAME: $*" >&2
  exit 1
}

# OS specific support (must be 'true' or 'false').
cygwin=false
msys=false
darwin=false
case "$(uname)" in
  CYGWIN*) cygwin=true ;;
  MINGW*) msys=true ;;
  Darwin*) darwin=true ;;
esac

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

if [ -z "$JAVA_HOME" ] ; then
  JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
fi

if [ ! -x "$JAVA_HOME/bin/java" ] ; then
  die "ERROR: JAVA_HOME is not set correctly."
fi

exec "$JAVA_HOME/bin/java" $DEFAULT_JVM_OPTS -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"

