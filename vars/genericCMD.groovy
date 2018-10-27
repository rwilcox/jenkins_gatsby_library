
def call( cmd ) {
    if ( isUnix() ) {
        sh cmd
    }
    else {
        bat cmd
    }
}

return this
