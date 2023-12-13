
/**
 * Called from the hamburger in the navbar, toggles the drawer on and off
 */
export function toggleDrawer() {
    const element = document.getElementById("drawer");
    if (element.classList.contains("hidden")) {
        element.classList.remove("hidden");
    } else {
        element.classList.add("hidden");
    }
}