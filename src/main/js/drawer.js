
/**
 * Called from the hamburger in the navbar, toggles the drawer on and off
 */
export function toggleDrawer() {
    const drawer = document.getElementById("navBarDrawer")
    const hamburger = document.getElementById("navBarHamburger")
    if (!drawer || !hamburger) {
        console.error("Incorrect navbar setup")
        return
    }

    // determine if the drawer is currently enabled or not
    const isDrawerEnabled = drawer.classList.contains("navbar-drawer-enabled")
    if (isDrawerEnabled) {
        drawer.classList.remove("navbar-drawer-enabled")
    } else {
        drawer.classList.add("navbar-drawer-enabled")
    }

    if(isDrawerEnabled) {
        hamburger.classList.remove("navbar-drawer-enabled")
    } else {
        hamburger.classList.add("navbar-drawer-enabled")
    }
}