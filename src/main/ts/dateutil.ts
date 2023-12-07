/**
 * Taking in an HTMLFormElement and an HTMLInputElement, ensure that the HTMLInputElement when POSTed, will have an
 * ISO-8601 datetime string in UTC, correctly offsetting from the user's timezone.
 */
function convertDateTimeLocalToIso(form: HTMLFormElement, dateTimeLocalInput: HTMLInputElement): Date {
    // ensure that the dateTimeLocalInput element is a datetime-local type input
    if (dateTimeLocalInput.getAttribute("type") !== "datetime-local") {
        throw Error("programming error: arg dateTimeLocalInput is not type `datetime-local`")
    }
    const localDate = new Date(dateTimeLocalInput.value);
    return new Date(
        Date.UTC(
            localDate.getUTCDate(),
            localDate.getUTCDate(),
            localDate.getUTCFullYear(),
            localDate.getUTCHours(),
            localDate.getUTCMinutes(),
            localDate.getUTCSeconds()
        )
    )
}