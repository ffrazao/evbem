package br.gov.df.emater.autorizador.controller;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ahmed on 21.5.18.
 */
@Controller
@RequestMapping("clients")
public class ClientsController {

	@Autowired
	private JdbcClientDetailsService clientsDetailsService;

	@RequestMapping(value = "{client.clientId}/delete", method = RequestMethod.POST)
	public String deleteClient(@ModelAttribute final BaseClientDetails ClientDetails,
			@PathVariable("client.clientId") final String id) {
		this.clientsDetailsService.removeClientDetails(this.clientsDetailsService.loadClientByClientId(id).toString());
		return "redirect:/";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_OAUTH_ADMIN')")
	public String editClient(@ModelAttribute final BaseClientDetails clientDetails,
			@RequestParam(value = "newClient", required = false) final String newClient) {
		if (newClient == null) {

			this.clientsDetailsService.updateClientDetails(clientDetails);
		} else {
			this.clientsDetailsService.addClientDetails(clientDetails);
		}

		if (!clientDetails.getClientSecret().isEmpty()) {
			this.clientsDetailsService.updateClientSecret(clientDetails.getClientId(), clientDetails.getClientSecret());
		}
		return "redirect:/";
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Collection.class, new SplitCollectionEditor(Set.class, ","));
		binder.registerCustomEditor(GrantedAuthority.class, new AuthorityPropertyEditor());
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_OAUTH_ADMIN')")
	public String showEditForm(@RequestParam(value = "client", required = false) final String clientId,
			final Model model) {

		ClientDetails clientDetails;
		if (clientId != null) {
			clientDetails = this.clientsDetailsService.loadClientByClientId(clientId);
		} else {
			clientDetails = new BaseClientDetails();
		}

		model.addAttribute("clientDetails", clientDetails);
		return "form";
	}
}
