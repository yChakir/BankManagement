export function includesIgnoreCase(obj: any, searchText: string, ...fields): boolean {
  if (!obj) {
    return false;
  }

  if (!searchText) {
    return true;
  }

  searchText = searchText.toLowerCase();

  let result: boolean = false;
  fields.forEach(field => {
    if (field && typeof field === "string" && obj[field]) {
      let text = obj[field].toString().toLowerCase();
      if (text.includes(searchText)) {
        result = true;
        return;
      }
    }
  });

  return result;
}
