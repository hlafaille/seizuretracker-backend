
/**
 * Called from the hamburger in the navbar, toggles the drawer on and off
 */
export function toggleDrawer() {
    const element = document.getElementById("navbarDrawer")
    if (!element) {
        console.error("An element with id `navbarDrawer` does not exist")
        return
    }
    if (element.classList.contains("hidden")) {
        element.classList.remove("hidden")
    } else {
        element.classList.add("hidden")
    }
}