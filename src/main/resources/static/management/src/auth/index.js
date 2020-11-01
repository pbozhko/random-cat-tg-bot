export const fakeAuth = {
    isAuthenticated: false,
    authenticate(cb) {
        this.isAuthenticated = true
        cb()
    },
    logout() {
        this.isAuthenticated = false
    }
}
