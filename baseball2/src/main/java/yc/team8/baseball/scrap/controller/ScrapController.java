package yc.team8.baseball.scrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yc.team8.baseball.scrap.domain.Scrap;
import yc.team8.baseball.scrap.dto.ScrapDto;
import yc.team8.baseball.scrap.service.ScrapService;

@Controller
public class ScrapController {
    @Autowired
    private ScrapService scrapService;

    @PostMapping("/{teamName}/{postType}/{id}/scrap")
    public String addScrap(ScrapDto scrapDto, @PathVariable String teamName,
                                @PathVariable String postType, @PathVariable Long id, RedirectAttributes rttr) {
        Scrap saved = scrapService.addScrap(scrapDto);
        rttr.addFlashAttribute("msg", "Scrap Success");
        return "redirect:/" + teamName + "/" + postType + "/" + id;
    }

    @DeleteMapping("/{teamName}/{postType}/{id}/scrap/delete")
    public String deleteScrap(ScrapDto scrapDto, @PathVariable String teamName,
                              @PathVariable String postType, @PathVariable Long id, RedirectAttributes rttr) {
        scrapService.deleteScrap(scrapDto.getId());
        rttr.addFlashAttribute("msg", "Scrap Canceled");
        return "redirect:/" + teamName + "/" + postType + "/" + id;
    }
}
