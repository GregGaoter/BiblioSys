export const dateToMonthNameYear = (date: string | undefined): string => {
  if (!date) {
    return "";
  }
  const dateSplit = date.toString().split(",", 2).reverse();
  return getMonthName(dateSplit[0]) + " " + dateSplit[1];
};

export const getMonthName = (monthNumber: string): string => {
  switch (monthNumber) {
    case "1":
      return "janvier";
    case "2":
      return "février";
    case "3":
      return "mars";
    case "4":
      return "avril";
    case "5":
      return "mai";
    case "6":
      return "juin";
    case "7":
      return "juillet";
    case "8":
      return "août";
    case "9":
      return "septembre";
    case "10":
      return "octobre";
    case "11":
      return "novembre";
    case "12":
      return "décembre";
    default:
      return "";
  }
};
