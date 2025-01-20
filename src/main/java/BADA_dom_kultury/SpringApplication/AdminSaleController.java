package BADA_dom_kultury.SpringApplication;


import BADA_dom_kultury.SpringApplication.DAO.SaleDAO;
import BADA_dom_kultury.SpringApplication.DAO.WydarzenieDAO;
import BADA_dom_kultury.SpringApplication.Tables.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/sales")
public class AdminSaleController {

    @Autowired
    private SaleDAO saleDAO;

    @Autowired
    private WydarzenieDAO wydarzenieDAO;

    @GetMapping
    public String showManageSalesPage(Model model) {
        List<Sale> sales = saleDAO.list();
        model.addAttribute("sales", sales);
        return "admin/manage-sales";  // Strona z tabelą sal
    }

    @GetMapping("/add")
    public String showAddSaleForm(Model model) {
        model.addAttribute("sale", new Sale());
        return "admin/add-sale";  // Formularz dodawania sali
    }

    @PostMapping("/add")
    public String addSale(@ModelAttribute Sale sale) {
        sale.setNr_domu_kultury(3);
        saleDAO.save(sale);
        return "redirect:/admin/sales";  // Po dodaniu przekierowanie do tabeli sal
    }

    @GetMapping("/edit")
    public String showEditSaleForm(@RequestParam int id, Model model) {
        Sale sale = saleDAO.get(id);
        model.addAttribute("sale", sale);
        return "admin/edit-sale";  // Formularz edycji sali
    }

    @PostMapping("/edit")
    public String editSale(@ModelAttribute Sale sale) {
        sale.setNr_domu_kultury(3);
        saleDAO.update(sale);
        return "redirect:/admin/sales";  // Po edycji przekierowanie do tabeli sal
    }

    @PostMapping("/delete")
    public String deleteSale(@RequestParam int id, Model model) {
        // Sprawdzamy, czy sala jest przypisana do jakiegoś wydarzenia
        boolean isEventAssigned = wydarzenieDAO.isEventAssigned(id);

        // Jeśli sala jest przypisana do wydarzenia, nie możemy jej usunąć
        if (isEventAssigned) {
            // Pobieramy wszystkie sale do ponownego wyświetlenia na stronie
            List<Sale> sales = saleDAO.list();

            // Dodajemy błąd do modelu
            model.addAttribute("sales", sales);
            model.addAttribute("error", "Nie można usunąć sali, ponieważ jest przypisana do wydarzenia. " +
                    "Najpierw pozmieniaj sale odpowiednich wydarzeń.");

            // Zwracamy widok z tabelą sal i komunikatem o błędzie
            return "admin/manage-sales";
        }

        // Jeśli nie ma przypisanych wydarzeń, usuwamy salę
        saleDAO.delete(id);

        // Po usunięciu przekierowujemy do tabeli sal
        return "redirect:/admin/sales";
    }
}
