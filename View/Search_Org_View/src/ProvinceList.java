public class ProvinceList
{
    private String province[] = new String[65];

    public ProvinceList()
    {
        for (int i=1; i<=64; i++)
            province[i] = new String();

        province[1] = "Hà Nội";
        province[2] = "Hồ Chí Minh";
        province[3] = "Hải Phòng";
        province[4] = "Đà Nẵng";
        province[5] = "Hà Giang";
        province[25] = "Nam Định";
        province[39] = "Phú Yên";
        province[43] = "Bình Phước";
        province[44] = "Bình Dương";
        province[45] = "Ninh Thuận";
        province[46] = "Tây Ninh";
        province[47] = "Bình Thuận";
        province[48] = "Đồng Nai";
        province[49] = "Long An";
        province[50] = "Đồng Tháp";
        province[51] = "An Giang";
        province[52] = "Bà Rịa - Vũng Tàu";
        province[53] = "Tiền Giang";
        province[54] = "Kiên Giang";
        province[55] = "Cần Thơ";
        province[56] = "Bến Tre";
        province[57] = "Vĩnh Long";
        province[58] = "Trà Vinh";
        province[59] = "Sóc Trăng";
    }

    public String getProvinceName(String Code)
    {
        int index = Integer.parseInt(Code);
        return province[index];
    }

    public String getProvinceCode(String Name)
    {
        for (int i = 1; i < 10; i++)
            if (province[i].equals(Name))
                return "0"+i+"";

        for (int i = 10; i <= 64; i++)
            if (province[i].equals(Name))
                return ""+i+"";

        return "";
    }
}
