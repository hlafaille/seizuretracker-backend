import('swiped-events')

/**
 * Handles swipe gesture for the html tag
 */
document.addEventListener('swiped-right', function(e) {
    /** @type {HTMLElement} */
    const targetElement = e.target
    if (!targetElement.classList.contains('navbar-drawer') || !targetElement.classList.contains('navbar-drawer-list') || !targetElement.classList.contains('navbar-drawer-list-item')) {
        toggleDrawer()
    }
})

/**
 * Handles swipe gesture for closing the navBarDrawer
 * @todo maybe change this to always toggle the drawer if the drawer is open?
 */
document.addEventListener('swiped-left', function(e) {
    /** @type {HTMLElement} */
    const targetElement = e.target
    if (targetElement.classList.contains('navbar-drawer') || targetElement.classList.contains('navbar-drawer-list') || targetElement.classList.contains('navbar-drawer-list-item')) {
        toggleDrawer()
    }
})

document.addEventListener('htmx:afterRequest', function(event) {
    if (event.target.id.includes('navBarDrawerItem')) {
        toggleDrawer()
    } else {
        console.log(event.target.id)
    }
})

/**
 * Listens for clicks on the navBarHamburger, toggling the drawer
 */
document.addEventListener('click', function(event) {
    if (event.target.getAttribute('id') === 'navBarHamburger') {
        toggleDrawer()
    }
})

/**
 * Toggles the drawer on and off
 */
export function toggleDrawer() {
    const drawer = document.getElementById('navBarDrawer')
    const hamburger = document.getElementById('navBarHamburger')
    if (!drawer || !hamburger) {
        console.error('Incorrect navbar setup. drawer==' + drawer + ' & hamburger==' + hamburger)
        return
    }

    // determine if the drawer is currently enabled or not
    const isDrawerEnabled = drawer.classList.contains('navbar-drawer-enabled')
    if (isDrawerEnabled) {
        drawer.classList.remove('navbar-drawer-enabled')
    } else {
        drawer.classList.add('navbar-drawer-enabled')
    }

    if (isDrawerEnabled) {
        hamburger.classList.remove('navbar-drawer-enabled')
    } else {
        hamburger.classList.add('navbar-drawer-enabled')
    }
}
