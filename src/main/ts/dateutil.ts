/**
 * Taking in an HTMLFormElement and an HTMLInputElement, ensure that the HTMLInputElement when POSTed, will have an
 * ISO-8601 datetime string in UTC, correctly offsetting from the user's timezone.
 */
void function convertDateTimeLocalToIso(form: HTMLFormElement, dateTimeLocalInput: HTMLInputElement) {
    // ensure that the dateTimeLocalInput element is a datetime-local type input
    if (dateTimeLocalInput.getAttribute("type") !== "datetime-local") {
        throw Error("programming error: arg dateTimeLocalInput is not type `datetime-local`")
    }


}